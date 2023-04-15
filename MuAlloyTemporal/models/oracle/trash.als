module Main

var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop1_faulty {
	no Trash + Protected
}

pred prop2_faulty {
	no File
  	some File'
}

pred prop3_faulty {
	always some File
}

pred prop4_faulty {
	eventually some Trash
}

pred prop5_faulty {
	eventually some f:File | f not in File'
}

pred prop6_faulty {
	always all f:Trash | always f in Trash
}

pred prop7_faulty {
	eventually some Protected
}

pred prop8_faulty {
	always all l : link.File | eventually l in Trash
}

pred prop9_faulty {
	always no Protected & Trash
}

pred prop10_faulty {
	always Protected' = Protected
}

pred prop11_faulty {
	always all f: File | f not in Protected implies f in Protected'
}

pred prop12_faulty {
	eventually some f : File | always f in Trash
}

pred prop13_faulty {
	always all f : Trash | once f not in Trash
}

pred prop14_faulty {
	always all f : Protected | f in Trash implies after f not in Protected
}

pred prop15_faulty {
	always all f : File | eventually f in Trash
}

pred prop16_faulty {
	always all f : Protected | historically f in Protected
}

pred prop17_faulty {
	always all f : Trash | f not in File'
}

pred prop18_faulty {
	always all f : Protected | f not in Protected' implies f in Trash
}

pred prop19_faulty {
	always all f : Protected | f in Protected until f in Trash
}

pred prop20_faulty {
	always all f:Trash | f in Trash since f not in Protected
}

run prop12_faulty