import React from 'react';

const Card = (props)=> {
	return (
  	<div style={{margin: '1em'}}>

      <div style={{display: 'inline-block', marginLeft:10}}>


        <div>{props.userId}</div>
      </div>
  	</div>
  );
};


export default Card;
