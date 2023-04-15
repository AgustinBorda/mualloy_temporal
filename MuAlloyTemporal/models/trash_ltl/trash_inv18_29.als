var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	always all t: Trash | once t in Protected and t not in Protected
}

run  prop18_faulty