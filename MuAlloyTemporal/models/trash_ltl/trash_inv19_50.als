var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	always (some Protected) until (Protected in Trash)
}

run  prop19_faulty