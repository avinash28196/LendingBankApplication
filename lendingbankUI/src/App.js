import React, { Component } from 'react';
import './App.css';
import CardList from './Modules/CardList.js'
import Form from './Modules/Form.js'

class App extends React.Component {

  state = {
	cards: []
};
  
addNewCard = (cardInfo) => {
	this.setState(prevState => ({
  	cards: prevState.cards.concat(cardInfo)
  }))
};

	render() {
  	return (
    	<div>
    	  <Form onSubmit={this.addNewCard}/>
        <CardList cards={this.state.cards} />
    	</div>
    );
  }
}

export default App;
