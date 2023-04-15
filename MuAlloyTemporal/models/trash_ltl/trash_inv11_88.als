var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
	always all f:File | f in Protected' iff f not in Protected
}

run  prop11_faulty