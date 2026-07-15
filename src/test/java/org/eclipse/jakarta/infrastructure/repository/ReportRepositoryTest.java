package org.eclipse.jakarta.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportRepositoryTest {

	private ReportRepository reportRepository;
	private ReportDto testReport1;
	private ReportDto testReport2;
	
	@BeforeEach
	void setup () {
		reportRepository = new ReportRepository();
		
		testReport1 = new ReportDto();
		testReport1.setTitle("Test Report 1");
		testReport1.setDetail("This is the first report.");
		
		testReport2 = new ReportDto();
		testReport2.setTitle("Test Report 2");
		testReport2.setDetail("This is the second report.");		
	}
	
	@AfterEach
	void teardown () {
		reportRepository = null;
		testReport1 = null;
		testReport2 = null;
	}
	
	/*
	 * 
	 */
	@Test
	void testCreate() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
		
		assertNotNull(reportRepository, "Report repository should not be null after adding reports");
		assertEquals(2, reportRepository.findAll().size());
	}
	
	/*
	 * 
	 */
	@Test
	void testViewingExistentReport() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
		
		assertAll(
				() -> assertEquals(2, reportRepository.findAll().size()),
				() -> assertEquals(testReport1,reportRepository.findReport(1)),
				() -> assertEquals(testReport2,reportRepository.findReport(2)),
				() -> assertEquals(1, testReport1.getId()),
				() -> assertEquals(2, testReport2.getId())
		);
	}

	/*
	 * This test checks that findReport returns a null value when the report id is nonexistent.
	 */
	@Test
	void testViewingNonexistentReport () {	
		assertAll(
				() -> assertEquals(0, reportRepository.findAll().size()),
				() -> assertNull(reportRepository.findReport(1))
		);
	}
	
	/*
	 * This test ensures that updating 
	 */
	@Test
	void testUpdatingExistingReport() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
		
		reportRepository.update(1, "Report 1 New Title", "Report 1 New Detail");
		reportRepository.update(2, "hatdog", "fishstick");
		
		assertAll(
			() -> assertEquals(testReport1.getTitle(), "Report 1 New Title"),
			() -> assertEquals(testReport1.getDetail(), "Report 1 New Detail"),
			() -> assertEquals(testReport2.getDetail(), "fishstick"),
			() -> assertEquals(testReport2.getTitle(), "hatdog"),
			() -> assertTrue(reportRepository.update(1, "test", "test"))
		);
	}
	
	/*
	 * This test ensures that updating nonexistent reports return false
	 */
	@Test
	void testUpdatingNonexistentReport() {

		assertAll(
				() -> assertFalse(reportRepository.update(3, "test", "test"))

		);
	}
	
	/*
	 * Tests for deletion of report in report repository
	 */
	@Test
	void testDeleteExistingReport() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
			
		assertAll(
			() -> assertEquals(2, reportRepository.findAll().size()),
			() -> reportRepository.delete(2),
			() -> assertFalse(reportRepository.findAll().contains(testReport2)),
			() -> assertTrue(reportRepository.findAll().contains(testReport1)),
			() -> assertEquals(1, reportRepository.findAll().size()),
			() -> reportRepository.delete(1),
			() -> assertEquals(0, reportRepository.findAll().size())
		);
	}
	
	@Test
	void testDeletingNonexistentReport() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
		
		reportRepository.delete(0);
		reportRepository.delete(3);
		
		assertAll(
				() -> assertEquals(2, reportRepository.findAll().size()),
				() -> assertEquals(testReport1,reportRepository.findReport(1)),
				() -> assertEquals(testReport2,reportRepository.findReport(2))
		);
	}
}
