ircLogBot
=========

ircLogBot is an app that

 - monitors a group of irc channels
 - logs activity on them
 - displays web apges with pointers to the logs

comes in two flavors:

ircLogBot atop pircBot (java):

- org/jibble/logbot/LogBotMultiChannelMain.java 
  created based on org/jibble/logbot/LogBotMain.java;
  kept the org/jibble/logbot package name for simplicity.

- html/index.html is just a static list of channels, dropped onto irc:/var/www/LogBot/.
  eventually, could connect, do a /list, and generate (+ connect based on) a dynamic list


ircLogBot atop jerk (javascript):

- irc bot library 'jerk', built atop node.js.
  sample in jerk/.  you'll need to have npm installed it.


