var jerk = require('jerk'), sys=require('sys');
var options =
  { server: 'irc'
  , port: 6667
  , nick: 'nodebot'
  , channels: [ '#test' ]
  };

jerk( function( j ) {
  j.watch_for( 'lunch', function( message ) {
    message.say( message.user + ': did you say lunch?!' )
  });

  j.watch_for( /^(.+) are silly$/, function( message ) {
    message.say( message.user + ': ' + message.match_data[1] + ' are NOT SILLY. Don\'t joke!' )
  });

  j.watch_for( /^(.+) is silly$/, function( message ) {
    message.say( message.user + ': ' + message.match_data[1] + ' are NOT SILLY. Don\'t joke!' )
  });

  j.watch_for( /bot/, function( message ) {
    message.say( message.user + ': ' + " There are not the bots your looking for." )
  });

  j.user_join(function(message) {
    message.say(message.user + ": welcome!");
  });

  j.user_leave(function(message) {
    sys.puts("later " + message.user + "!  ciao.");
  });
}).connect( options );


