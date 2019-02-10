package com.LendingClub.demo.config;

import com.LendingClub.demo.model.LendingClub;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classPath:/input/input.csv")
    private Resource inputResource;

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<LendingClub, LendingClub>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemProcessor<LendingClub, LendingClub> processor() {
        return new DBLogProcessor();
    }

    @Bean
    public FlatFileItemReader<LendingClub> reader() {
        FlatFileItemReader<LendingClub> itemReader = new FlatFileItemReader<LendingClub>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }

    @Bean
    public LineMapper<LendingClub> lineMapper() {
        DefaultLineMapper<LendingClub> lineMapper = new DefaultLineMapper<LendingClub>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"member_id", "loan_amnt", "funded_amnt_inv", "term", "int_rate", "installment", "grade", "emp_title", "emp_length", "home_ownership", "annual_inc", "verification_status",  "issue_d", "loan_status", "descr", "purpose", "title", "addr_state", "last_pymnt_d", "last_pymnt_amnt"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19});
        BeanWrapperFieldSetMapper<LendingClub> fieldSetMapper = new BeanWrapperFieldSetMapper<LendingClub>();
        fieldSetMapper.setTargetType(LendingClub.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<LendingClub> writer() {
        JdbcBatchItemWriter<LendingClub> itemWriter = new JdbcBatchItemWriter<LendingClub>();
        itemWriter.setDataSource(dataSource());
        itemWriter.setSql("INSERT INTO LENDINGCLUB ( MEMBER_ID,LOAN_AMNT,FUNDED_AMNT_INV,TERM,INT_RATE,INSTALLMENT,GRADE,EMP_TITLE,EMP_LENGTH,HOME_OWNERSHIP,ANNUAL_INC,VERIFICATION_STATUS,ISSUE_D,LOAN_STATUS,DESCR,PURPOSE,TITLE,ADDR_STATE,LAST_PYMNT_D,LAST_PYMNT_AMNT ) VALUES ( :member_id, :loan_amnt, :funded_amnt_inv, :term, :int_rate, :installment, :grade, :emp_title, :emp_length, :home_ownership, :annual_inc, :verification_status, :issue_d, :loan_status, :descr, :purpose, :title, :addr_state, :last_pymnt_d, :last_pymnt_amnt )");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<LendingClub>());
        return itemWriter;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:lendingClub.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
