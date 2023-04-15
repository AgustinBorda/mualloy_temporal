var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop9_faulty { 
	always (Protected not in Trash)
}

run  prop9_faulty