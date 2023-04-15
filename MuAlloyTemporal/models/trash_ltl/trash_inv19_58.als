var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	always (all f:Protected | f in Trash releases f in Protected)
}

run  prop19_faulty