var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	always all f : File - Protected | eventually f in Trash
  	always all f : Trash - Protected | f in Trash'
}

run  prop12_faulty