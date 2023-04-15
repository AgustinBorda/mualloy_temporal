var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	all f : File | some z : f.link | eventually (f in Trash and z in Trash) 
}

run  prop8_faulty