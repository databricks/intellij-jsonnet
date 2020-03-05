//local a = {
//  d: 0,
//  local outer = self + {x: 1},
//  b: { c: outer, c2: a.d + 1, d: a},
//};
//
//local e = a.b { d: 4 };
//local e2 = (a { d: 4 }).b;


//[e2.c.x, e.d, e.c.b.d, e.d, f3.w]


local f1 = {y: "y", z: "z"};
local f2 = {[x]: x for x in [1,2,3]};
local f3 = if(true) then f1 else f2;
f3.z
