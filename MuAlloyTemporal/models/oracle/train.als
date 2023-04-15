// Model of a train station

sig Track {
	prox : set Track,
	signal : lone Signal
}
sig Junction extends Track {}
sig Entry, Exit in Track {}

sig Signal {}
var sig Green in Signal {}

sig Train {
	var pos : lone Track
}

fact Layout {
	// A track is a junction iff it has more than one successor or more than one predecessor
	all t : Track | t not in Junction iff (lone t.prox and lone prox.t)
	// No cycles
	no t : Track | t in t.^prox
	// Signals belong to one and only one track
	all s : Signal | one signal.s
	// All tracks before junctions have signals
	all j : Junction, t : prox.j | some t.signal
	// Entry tracks are those without predecessors and exit tracks are those without no successors
	all t : Track | t in Entry iff no prox.t
	all t : Track | t in Exit iff no t.prox
}

pred prop1_faulty {
	no Green
}

pred prop2_faulty {
	all s : Signal | eventually s in Green
}

pred prop3_faulty {
	always pos' = pos
}

pred prop4_faulty {
	always all t : Track | lone pos.t
}

pred prop5_faulty {
	all t : Train | always (some t.pos implies (t.pos' = t.pos or (t.pos in Exit implies no t.pos' else (some t.pos' and t.pos' in t.pos.prox))))
}

pred prop6_faulty {
	all s : Signal | always eventually (s in Green) and always eventually (s not in Green)
}

pred prop7_faulty {
	all t : Train | always (some t.pos implies eventually no t.pos)
}

pred prop8_faulty {
	all t : Train, p : Track | always (t.pos = p and p.signal not in Green implies (p.signal in Green releases t.pos = p))
}

pred prop9_faulty {
	all t : Train | no t.pos until (some t.pos & Entry)
}

pred prop10_faulty {
	all j : Junction | always lone (prox.j).signal & Green	
}

pred prop11_faulty {
	all t : Train | always (some t.pos implies once some t.pos & Entry)
}

pred prop12_faulty {
	all t : Train | always (some t.pos implies some e : *prox.(t.pos) & Entry | all x : *prox.(t.pos) & e.*prox | once t.pos = x)
}

pred prop13_faulty {
	all t : Train | always ((no t.pos and once some t.pos) implies always no t.pos)
}

pred prop14_faulty {
	all s : Signal, t : Train | always (s in Green and t.pos = signal.s and t.pos' != signal.s implies after s not in Green)
}

pred prop15_faulty {
	all t : Train, p : Track | not (eventually always t.pos = p)
}

pred prop16_faulty {
	all t : Train | always (some t.pos & Exit implies (some t.pos since some t.pos & Entry))
}

pred prop17_faulty {
	all t : Train | always ((some t.pos and historically no (Train-t).pos) implies (no Train.pos & Exit until some t.pos & Exit))
}

pred prop18_faulty {
	all j : Junction | always (all disj x,y : pos.(prox.j) | before (x in pos.(prox.j) and y not in pos.(prox.j)) implies (x in pos.j releases (y not in pos.j)))
}

run prop1_faulty