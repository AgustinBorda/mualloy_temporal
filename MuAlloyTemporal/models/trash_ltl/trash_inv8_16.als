var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	all l: File.link | eventually l in Trash
}

run  prop8_faulty