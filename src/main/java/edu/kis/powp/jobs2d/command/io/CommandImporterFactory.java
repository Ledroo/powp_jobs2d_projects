package edu.kis.powp.jobs2d.command.io;

/**
 * Facility responsible for resolving and returning the appropriate
 * CommandImporter instance based on file contents or extension.
 */
public class CommandImporterFactory {

    /**
     * Given the text content, returns the correct CommandImporter.
     * 
     * @param text content of the command file
     * @return adequate CommandImporter strategy
     * @throws IllegalArgumentException if no importer matches the content format
     */
    public static CommandImporter getImporter(String text) {
        String trimmedText = text.trim();

        if (trimmedText.startsWith("[") || trimmedText.startsWith("{")) {
            return new JsonCommandImporter();
        }

        throw new IllegalArgumentException("TXT file format not recognized. Was expecting JSON.");
    }
}
