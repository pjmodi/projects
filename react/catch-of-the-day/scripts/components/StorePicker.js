import React from 'react';
import { History } from 'react-router';
import h from '../helpers';

var StorePicker = React.createClass({
    mixins : [History],

    goToStore : function(event) {
        event.preventDefault();

        // get the data from the input
        // console.log(this.refs);
        var storeId = this.refs.storeId.value;

        // transition to new route
        this.history.pushState(null, '/store/' + storeId);
    },

    // render method is the html that will be displayed.
    render : function() {
        var name = "Pushkar";
        return (
            <form className="store-selector" onSubmit={this.goToStore}>
                {/* Comment goes inside curly
                 braces like this here! */}
                <h2>Please enter a store {name}</h2>
                <input type="text" ref="storeId" defaultValue={h.getFunName()} />
                <input type="Submit"/>
            </form>
        )
    }
});

export default StorePicker;