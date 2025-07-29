import React, { useState } from 'react';
import {
  Box, Button, Input, Text, VStack, useToast, Spinner, Icon, Heading,
  Flex, HStack, Divider
} from '@chakra-ui/react';
import { FaCloudUploadAlt } from 'react-icons/fa';
import axios from 'axios';

const UploadForm = () => {
  const [file, setFile] = useState(null);
  const [isUploading, setIsUploading] = useState(false);
  const toast = useToast();

  const handleFileChange = (e) => {
    const selected = e.target.files[0];
    if (selected && (selected.type === 'audio/mpeg' || selected.type === 'audio/wav')) {
      setFile(selected);
    } else {
      toast({
        title: 'Invalid file type',
        description: 'Only .mp3 or .wav files are allowed.',
        status: 'error',
        duration: 3000,
        isClosable: true,
      });
    }
  };

  const handleUpload = async () => {
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    setIsUploading(true);
    try {
      const res = await axios.post('http://localhost:8080/api/upload', formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });

      toast({
        title: 'Upload successful',
        description: res.data.message || 'File has been uploaded!',
        status: 'success',
        duration: 3000,
        isClosable: true,
      });

      setFile(null);
    } catch (error) {
      toast({
        title: 'Upload failed',
        description: error.message || 'Something went wrong',
        status: 'error',
        duration: 3000,
        isClosable: true,
      });
    } finally {
      setIsUploading(false);
    }
  };

  return (
    <Flex align="center" justify="center" minH="100vh" bg="gray.100">
      <Box
        w="full"
        maxW="lg"
        p={8}
        bg="white"
        borderRadius="2xl"
        boxShadow="2xl"
      >
        <VStack spacing={6}>
          <Heading size="lg" textAlign="center" color="teal.600">
            Upload Meeting Audio
          </Heading>

          <Box
            border="2px dashed"
            borderColor="gray.300"
            borderRadius="xl"
            p={8}
            w="full"
            textAlign="center"
            bg="gray.50"
          >
            <Icon as={FaCloudUploadAlt} w={12} h={12} color="teal.500" />
            <Text mt={4} fontSize="sm" color="gray.600">
              Drag & drop your audio file here, or click to browse
            </Text>
            <Input
              type="file"
              accept=".mp3, .wav"
              onChange={handleFileChange}
              mt={4}
              variant="unstyled"
              w="full"
              cursor="pointer"
              p={2}
              border="1px solid transparent"
              _hover={{ borderColor: 'gray.300' }}
              bg="white"
            />
            {file && (
              <Text mt={2} fontSize="sm" color="teal.600">
                Selected: {file.name}
              </Text>
            )}
          </Box>

          <HStack spacing={4} w="full" justify="center">
            <Button
              colorScheme="teal"
              onClick={handleUpload}
              isDisabled={!file || isUploading}
              w="40"
            >
              {isUploading ? <Spinner size="sm" /> : 'Upload'}
            </Button>
            {file && (
              <Button
                variant="ghost"
                onClick={() => setFile(null)}
                colorScheme="gray"
              >
                Clear
              </Button>
            )}
          </HStack>

          <Divider />
          <Text fontSize="xs" color="gray.400" textAlign="center">
            Only .mp3 or .wav audio files up to 50MB allowed
          </Text>
        </VStack>
      </Box>
    </Flex>
  );
};

export default UploadForm;
