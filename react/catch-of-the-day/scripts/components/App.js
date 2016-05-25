import React from 'react';
import Catalyst from 'react-catalyst';

import Fish from './Fish';
import Header from './Header';
import Order from './Order';
import Inventory from './Inventory';

// Firebase
import Rebase from 're-base';
var base = Rebase.createClass('https://glaring-fire-8643.firebaseio.com/');

var App = React.createClass({
    mixins : [Catalyst.LinkedStateMixin],

    getInitialState : function() {
        return {
            fishes : {},
            order : {}
        }
    },

    componentDidMount : function() {
        base.syncState(this.props.params.storeId + '/fishes', {
            context : this,
            state : 'fishes'
        });

        var localStorageRef = localStorage.getItem('order-' + this.props.params.storeId);

        if(localStorageRef) {
            this.setState({
                order : JSON.parse(localStorageRef)
            });
        }
    },

    componentWillUpdate : function(nextProps, nextState) {
        localStorage.setItem('order-' + this.props.params.storeId, JSON.stringify(nextState.order));
    },

    addToOrder : function(fish) {
        this.state.order[fish] = this.state.order[fish] + 1 || 1;
        this.setState({order : this.state.order });
    },

    removeFromOrder : function(fish) {
        delete this.state.order[fish];
        this.setState({order : this.state.order });
    },

    addFish : function(fish) {
        var timestamp = (new Date()).getTime();

        // update the state object
        this.state.fishes['fish-' + timestamp] = fish;

        // set the state
        this.setState({ fishes : this.state.fishes });
    },

    removeFish : function(fish) {
        if(confirm("Are you sure you want to remove this fish?")) {
            this.state.fishes[fish] = null;
            this.setState({
                fishes : this.state.fishes
            });
        }
    },

    loadSamples: function() {
        this.setState({
            fishes : require('../sample-fishes')
        });
    },

    renderFish: function(fish) {
        return (
            <Fish key={fish} index={fish} details={this.state.fishes[fish]} addToOrder={this.addToOrder} />
        )
    },

    render : function() {
        return (
            <div className="catch-of-the-day">
                <div className="menu">
                    <Header tagline="Fresh Seafood Market" />
                    <ul className="list-of-fishes">
                        {Object.keys(this.state.fishes).map(this.renderFish)}
                    </ul>
                </div>
                <Order fishes={this.state.fishes} order={this.state.order} removeFromOrder={this.removeFromOrder} />
                <Inventory
                    addFish={this.addFish} loadSamples={this.loadSamples} fishes={this.state.fishes}  linkState={this.linkState} removeFish={this.removeFish} />
            </div>
        )
    }
});

export default App;