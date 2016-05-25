var React = require('react');
var ReactDOM = require('react-dom');

var ReactRouter = require('react-router');
var Router= ReactRouter.Router;
var Route= ReactRouter.Route;
var createBrowserHistory = require('history/lib/createBrowserHistory');


// Firebase
var Rebase = require('re-base');
var base = Rebase.createClass('https://glaring-fire-8643.firebaseio.com/');

var Catalyst = require('react-catalyst');

// Import Components
import NotFound from './components/NotFound';
import StorePicker from './components/StorePicker';
import Fish from './components/Fish';
import Header from './components/Header';
import Order from './components/Order';
import Inventory from './components/Inventory';

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
            fishes : require('./sample-fishes')
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

var routes = (
    <Router history={createBrowserHistory()}>
        <Route path="/" component={StorePicker} />
        <Route path="/store/:storeId" component={App} />
        <Route path="*" component={NotFound} />
    </Router>
)

ReactDOM.render(routes, document.querySelector('#main'));

