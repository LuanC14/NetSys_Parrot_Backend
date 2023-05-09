package com.SocialNetSys.NetSys.Providers;

import java.util.UUID;

public class FilenameCreator {
     public static String nameGenerator(UUID entityId, String originalFilename ) {
         return entityId + "." + originalFilename.lastIndexOf(".")+1; // Retornará por exemplo: nomeDaEntidade.nomeDoArquivo.jpg
     }
}
