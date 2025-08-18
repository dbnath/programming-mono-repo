variable "resource_group_name" {
  type = string
}

variable "container_app_environment_id" {
  type = string
}

variable "container_app_name" {
  type    = string
  default = "pgvector"
}

variable "image" {
  type    = string
  default = "pgvector/pgvector:pg17"
}
