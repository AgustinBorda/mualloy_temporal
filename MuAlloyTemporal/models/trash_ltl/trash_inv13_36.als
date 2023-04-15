var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop13_faulty { 
	File in Trash implies once no Trash
}

run  prop13_faulty