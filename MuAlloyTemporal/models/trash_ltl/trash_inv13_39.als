var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop13_faulty { 
	always (File in Trash implies once File not in Trash)
}

run  prop13_faulty