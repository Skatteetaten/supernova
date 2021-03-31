package no.skatteetaten.nebula.demo.supernova.blob

import org.testcontainers.containers.GenericContainer

class AzuriteContainer : GenericContainer<AzuriteContainer>("mcr.microsoft.com/azure-storage/azurite")