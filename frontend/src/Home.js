import React, { Component } from 'react';
import './App.css';
import Dashboard from './Dashboard';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
    render() {
        return (
            <div>
                <Dashboard/>
            </div>
        );
    }
}

export default Home;