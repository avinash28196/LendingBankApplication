import React from 'react';
import Card from './Card.js'

const CardList = (props) => {
	return (
  	<div>
    	{props.cards.map(card => <Card key={card.id} {...card}/>)}
  	</div>
  );
}

export default CardList;
