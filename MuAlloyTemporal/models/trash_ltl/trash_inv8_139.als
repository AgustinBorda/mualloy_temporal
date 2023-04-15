var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
  some f : File | eventually f.link + link.f in Trash 
}

run  prop8_faulty