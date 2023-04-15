var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
	some File && no Trash
  	eventually some Trash
}

run  prop4_faulty