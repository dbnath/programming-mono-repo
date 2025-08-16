variable "resource_group_name" {
  type        = string
  default     = "rg-pgvector"
  description = "The name of the resource group."
}

variable "location" {
  type        = string
  default     = "East US"
  description = "The Azure region where resources will be created."
}

variable "container_app_environment_name" {
  type        = string
  default     = "cae-pgvector"
  description = "The name of the container app environment."
}

variable "container_app_name" {
  type        = string
  default     = "pgvector"
  description = "The name of the container app."
}

variable "container_image" {
  type        = string
  default     = "pgvector/pgvector:pg17"
  description = "The container image to deploy."
}

variable "postgres_password" {
  type        = string
  sensitive   = true
  default     = "postgres"
  description = "The password for the PostgreSQL database."
}
