var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
  always all l: File.link | eventually l in Trash since l in File.link
}

run  prop8_faulty