function makeArray() {
     for (i = 0; i<makeArray.arguments.length; i++)
          this[i + 1] = makeArray.arguments[i];
}

var months = new makeArray('January','February','March',
    'April','May','June','July','August','September',
    'October','November','December');

var date = new Date();
var month = date.getMonth() + 1;
var day  = date.getDate();
var yy = date.getYear();
var year = (yy < 1000) ? yy + 1900 : yy;

document.write(months[month] + " " + day + ", " + year);
