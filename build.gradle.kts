plugins {
	id("dev.frozenmilk.teamcode") version "11.1.0-1.1.2"
	id("dev.frozenmilk.sinister.sloth.load") version "0.2.4"
}

ftc {
	sdk {
		TeamCode()
	}
	dairy {
		implementation(Sloth)
	}
}
