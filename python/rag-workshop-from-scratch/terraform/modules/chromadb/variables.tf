variable "resource_group_name" {
  type = string
}

variable "container_app_environment_id" {
  type = string
}

variable "container_app_name" {
  type    = string
  default = "chromadb"
}

variable "image" {
  type    = string
  default = "chromadb/chroma"
}

variable "container_cpu" {
  type    = number
  default = 0.5
}

variable "container_memory" {
  type    = string
  default = "1Gi"
}

variable "target_port" {
  type    = number
  default = 8000
}

variable "exposed_port" {
  type    = number
  default = 8000
}
