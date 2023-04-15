var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	always some f:File | f not in Trash implies f in Trash'
}

run  prop12_faulty