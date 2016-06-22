// a reducer takes in two thing:
// 1. action
// 2. copy of current state

function posts(state = [], action) {
	console.log("This post will change");
	console.log(state, action);
	return state;
}

export default posts;