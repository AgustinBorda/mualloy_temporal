var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	eventually all f:File | eventually f in Trash implies f in Trash
}

run  prop12_faulty