import React, { Component } from 'react';
import axios from 'axios';

class Form extends React.Component {
	state = { userId: '' }


  handleSubmit = (event) => {
  	event.preventDefault();

    axios.get(`http://localhost:9090/lendingClub/${this.state.userId}`)
    .then(json => console.log(json))

    // axios.get(`https://cors.io/?http://://localhost:9090/lendingClub/${this.state.userId}`)
    // .then(resp => {
    // 	this.props.onSubmit(resp.data);
    //   this.setState({ userId: '' })
    // });
  };

  render () {
  	return (
    	<form onSubmit={this.handleSubmit}>
    	  <input type="text"
        value={this.state.userId}
        onChange={(event) => this.setState({ userId: event.target.value })}
        placeholder="Enter MemeberId 1066717"/>
        <button type="submit">Search</button>
    	</form>
    );
  }
}

export default Form;
