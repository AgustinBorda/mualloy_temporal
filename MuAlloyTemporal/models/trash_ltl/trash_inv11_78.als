var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
	always all up:File-Protected+Trash | up' in Protected 
}

run  prop11_faulty