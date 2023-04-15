var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop4_faulty { 
  	some File
	some f:File | eventually f in Trash
}

run  prop4_faulty