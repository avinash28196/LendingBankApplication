package com.LendingClub.demo.config;
 
import com.LendingClub.demo.model.LendingClub;
import org.springframework.batch.item.ItemProcessor;


 
public class DBLogProcessor implements ItemProcessor<LendingClub, LendingClub>
{
    public LendingClub process(LendingClub lendingClub) throws Exception
    {
        System.out.println("Inserting Club : " + lendingClub);
        return lendingClub;
    }
}