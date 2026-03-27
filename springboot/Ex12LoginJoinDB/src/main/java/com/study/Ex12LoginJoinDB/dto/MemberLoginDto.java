@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValid(MethodArgumentNotValidException e) {
        List<String> fieldOrder = List.of("memberUsername", "memberPassword");

        Map<String, String> errorMap = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(
                        Collectors
                        .toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (existing, replacement) -> existing
                        )
                );

        for (String field : fieldOrder) {
            if (errorMap.containsKey(field)) {
                return ResponseEntity.status(400).body(errorMap.get(field));
            }
        }

        return ResponseEntity.status(400).body("입력값을 확인해주세요.");
    }

@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }