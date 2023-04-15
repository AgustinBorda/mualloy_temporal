var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	all f:File | f in (Trash & Protected) implies f not in Protected'
}

run  prop14_faulty