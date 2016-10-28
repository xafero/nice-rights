package com.xafero.nice.rights;

import static com.xafero.nice.rights.util.AnyId.iid;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

import com.xafero.nice.rights.api.IExaminer;
import com.xafero.nice.rights.example.Game;
import com.xafero.nice.rights.example.Permission;
import com.xafero.nice.rights.example.User;
import com.xafero.nice.rights.rdf.TripleExaminer;
import com.xafero.nice.rights.util.AnyId.IdMode;

public class IExaminerTest {

	@Test
	public void testCheck() throws IOException {		
		try (IExaminer miner = new TripleExaminer("src/test/resources/drm.nt")) {
			boolean isAllowed = miner.isAllowed(new User("Franz"),
					Permission.Views, new Game("Pacman"));
			assertTrue(isAllowed);
			isAllowed = miner.isAllowed(iid("Herbert", "User"), 
					iid(Permission.Plays, "Perm", IdMode.Lower),
					iid("Chess", "Game"));
			assertTrue(isAllowed);
			isAllowed = miner.isAllowed(new User("Harry"), 
					Permission.Plays, new Game("Pacman"));
			assertFalse(isAllowed);
		}
	}
}
