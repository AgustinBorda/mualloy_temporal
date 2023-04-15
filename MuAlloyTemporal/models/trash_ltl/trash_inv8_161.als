var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	eventually (some f : File | some f.link implies f in Trash)
}

run  prop8_faulty