var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	always all p : Protected | eventually p in Trash && (always p not in Trash => p in Protected)
}

run  prop19_faulty