var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop20_faulty { 
	always all p : File | p in Trash since p not in Protected
}

run  prop20_faulty