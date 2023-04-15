var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	eventually (all t: Trash | Trash' = Trash - t)
}

run  prop5_faulty