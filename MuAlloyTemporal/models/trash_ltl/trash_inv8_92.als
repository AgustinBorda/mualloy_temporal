var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	all f : File.link | f not in Trash and eventually f in Trash
}

run  prop8_faulty