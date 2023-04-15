var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	
  	always (some File.link implies eventually File.link in Trash)	
  	
}

run  prop8_faulty