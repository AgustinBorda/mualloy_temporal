var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
  always all f : File - Protected | before f in Trash
}

run  prop18_faulty