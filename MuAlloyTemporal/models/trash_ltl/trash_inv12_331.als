var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	eventually File in Trash implies always File in Trash
}

run  prop12_faulty