var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	some t: Trash | eventually (t not in File)
}

run  prop5_faulty