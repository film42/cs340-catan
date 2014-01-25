var catan = catan || {};
catan.util = catan.util || {};
catan.util.dice = catan.util.dice || {};

// We don't need to instantiate, we're just calling roll
catan.util.dice.roll = function rollDie() {
  return 2;
};