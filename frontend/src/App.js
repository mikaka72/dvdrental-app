import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ClientList from './ClientList';
import ClientEdit from './ClientEdit';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/customers' exact={true} component={ClientList}/>
            <Route path='/customers/:id' component={ClientEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;
